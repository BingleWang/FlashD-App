package cn.jeefast.system.controller;


import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.Assert;
import cn.jeefast.system.entity.*;
import cn.jeefast.system.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  front controller
 * </p>
 *
 */
@RestController
@RequestMapping("/sysIndexQd")
public class SysIndexQdController extends BaseController {

    @Value("${server.port}")
    private String serverport;

    @Value("${server.context-path}")
    private String servercontextpath;


    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDingdanService sysDingdanService;

    @Autowired
    private TMaterialFileService tMaterialFileService;


    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * loginusername view
     */
    @RequestMapping("/getUserDl")
    public R getUserDl(@RequestBody Map<String,Object> tjarray) throws UnknownHostException {
        boolean sfxsdl = false;
        String dlry = "";
        if(tjarray.get("token") != null){
            SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token",tjarray.get("token") +""));
            if(sysUserToken != null){
                SysUser user = sysUserService.selectById(sysUserToken.getUserId());
                if(user != null){
                    dlry = user.getUsername();
                    sfxsdl = true;
                }
            }
        }
        return R.ok().put("sfxsdl",sfxsdl).put("dlry",dlry);
    }

    /**
     * get userinfo
     */
    @RequestMapping("/getUser")
    public R getUser(@RequestBody Map<String,Object> tjarray) throws UnknownHostException {
        System.out.println("tjarraytjarray"+tjarray);

        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token",tjarray.get("token")+""));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());

        /**
         */
        List<TMaterialFile> tMaterialFileList = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",user.getUserId()));
//        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("user_id", getUserId()));
        System.out.println("tMaterialFileListtMaterialFileList"+tMaterialFileList);
        InetAddress address = InetAddress.getLocalHost();
        user.setPhotopath(tMaterialFileList != null && tMaterialFileList.size()>0?"http://"+address.getHostAddress() +":"+serverport+"/"+servercontextpath+"/upload/"+tMaterialFileList.get(0).getSfilename()+"?token="+sysUserToken.getToken():"img/usermm.jpg");
        System.out.println("useruseruseruser"+user);


        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
        user.setRoleIdList(roleIdList);

        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",user.getUserId()));
        List<Map<String,Object>> mapList = new ArrayList<>();
        if(!tMaterialFiles.isEmpty()){
            for(TMaterialFile tMaterialFile:tMaterialFiles){
                Map<String,Object> map =new HashMap<>();
                map.put("id",tMaterialFile.getId());
                map.put("filePath",tMaterialFile.getSfilename());
                map.put("fileName",tMaterialFile.getSaccessoryname());
                mapList.add(map);
            }
        }
        JSONArray json = (JSONArray) JSONArray.toJSON(mapList);
        user.setFiles(json);

        return R.ok().put("sysuser",user);

    }

    /**
     */
    @RequestMapping("/userSaveFile")
    public R userSaveFile(@RequestBody JSONObject param){
        System.out.println("paramparamparam"+JSON.toJSONString(param));
        String userid = param.getString("userid");
        JSONArray allFiles = param.getJSONArray("allFiles");
        System.out.println("useriduseriduserid"+userid);
        System.out.println("allFilesallFilesallFiles"+JSON.toJSONString(allFiles));
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",userid));
        if (tMaterialFiles.size()>0){
            for (int i = 0; i < tMaterialFiles.size(); i++) {
                tMaterialFileService.deleteById(tMaterialFiles.get(i).getId());
            }
        }
        if(allFiles.size()>0){
            for (int i = 0; i < allFiles.size(); i++) {
                Map map = (Map) allFiles.get(i);
                TMaterialFile tMaterialFile = tMaterialFileService.selectById(map.get("id").toString());
                tMaterialFile.setParentid(userid);
                tMaterialFileService.updateById(tMaterialFile);
            }
        }
        return R.ok();
    }

    /**
     */
    @RequestMapping("/updateuser")
    public R updateuser(@RequestBody SysUser sysUser){
        System.out.println("sysUsersysUser"+sysUser);
        sysUserService.updateById(sysUser);
        return R.ok();

    }

    /**
     */
    @RequestMapping("/updatepassword")
    public R updatepassword(@RequestBody JSONObject tjarray){
        String token = tjarray.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token",token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());


        String oldpassword = tjarray.getString("oldpassword");
        String newPassword =  tjarray.getString("password");


        Assert.isBlank(newPassword, "new Password cannot be empty");

        oldpassword = new Sha256Hash(oldpassword, user.getSalt()).toHex();
        newPassword = new Sha256Hash(newPassword, user.getSalt()).toHex();
        if(!oldpassword.equals(user.getPassword())){
            return R.error("old Incorrect password No cannot update password");
        }

//        SysUser user = new SysUser();
        user.setUserId(user.getUserId());
        user.setPassword(newPassword);
        //更new password
        sysUserService.updateById(user);
        return R.ok();

    }


    /**
     */
    @RequestMapping("/qrsh")
    public R qrsh(@RequestBody Map<String,Object> temp){
        String[] dingdanidsArray = (temp.get("dingdanids")+"").replace("\"", "").split(",");
        for(String dingdanid:dingdanidsArray){
            SysDingdan sysDingdan = sysDingdanService.selectById(dingdanid);
            sysDingdan.setSfqrsh("1");
            sysDingdanService.updateById(sysDingdan);
        }
        return R.ok();

    }

    @RequestMapping("/saveDingdan")
    public R saveDingdan(@RequestBody JSONObject param){
        String token = param.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token",token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());

        String type = param.getString("type");
        String name = param.getString("name");
        Double zl = param.getDouble("zl");
        Integer sl = param.getInteger("sl");
        String shrxm = param.getString("shrxm");
        String shrlxfs = param.getString("shrlxfs");
        String shdz = param.getString("shdz");
        String fhrxm = param.getString("fhrxm");
        String fhrlxfs = param.getString("fhrlxfs");
        String fhdz = param.getString("fhdz");
        String bz = param.getString("bz");

        Double ddje = param.getDouble("ddje");
        String ybone = param.getString("ybone");
        String ybtwo = param.getString("ybtwo");

        SysDingdan sysDingdan = new SysDingdan();

        sysDingdan.setDdje(ddje);
        sysDingdan.setYbone(ybone);
        sysDingdan.setYbtwo(ybtwo);
        sysDingdan.setDdtype(type);
        sysDingdan.setYdry(user.getUsername());
        sysDingdan.setName(name);
        sysDingdan.setZl(zl);
        sysDingdan.setSl(sl);
        sysDingdan.setShrxm(shrxm);
        sysDingdan.setShrlxfs(shrlxfs);
        sysDingdan.setShdz(shdz);
        sysDingdan.setFhrxm(fhrxm);
        sysDingdan.setFhrlxfs(fhrlxfs);
        sysDingdan.setFhdz(fhdz);
        sysDingdan.setBz(bz);
        sysDingdan.setSfqrsh("1");
        sysDingdan.setKssj(new Date());

        sysDingdan.setUpdateuser(user.getUsername());
        sysDingdan.setUpdatetime(new Date());
        sysDingdanService.insert(sysDingdan);
        return R.ok();

    }

    @Autowired
    private SysTypeService sysTypeService;

    @RequestMapping("/getTypes")
    public R getTypes(@RequestBody JSONObject param){
        List<SysType> types = sysTypeService.selectList(new EntityWrapper<SysType>().orderBy(true,"xh",true));
        System.out.println("getTypesgetTypes"+types);
        return R.ok().put("types",types);
    }

    @RequestMapping("/getDingdanListAll")
    public R getDingdanListAll(@RequestBody JSONObject param){
        String token = param.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token",token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());

        List<SysDingdan> dingdanList = sysDingdanService.selectList(new EntityWrapper<SysDingdan>().eq("customeraccount",user.getUsername()).orderBy(true,"updatetime",false));
        if(dingdanList.size()>0){
            for (int i = 0; i < dingdanList.size(); i++) {
                SysDingdan sysDingdan = dingdanList.get(i);
                if(sysDingdan.getDdfzr() != null){
                    SysUser syswlyUser = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username",sysDingdan.getDdfzr()));
                    if(syswlyUser != null){
                        sysDingdan.setYsdh(syswlyUser.getMobile());
                    }
                }
                SysType sysType = sysTypeService.selectById(sysDingdan.getDdtype());
                sysDingdan.setTypename(sysType.getName());

                if(sysDingdan.getSfqrsh() != null){
                    if(sysDingdan.getSfqrsh().equals("1")){
                        sysDingdan.setSfqrshname("Pending items");
                    }else if(sysDingdan.getSfqrsh().equals("2")){
                        sysDingdan.setSfqrshname("Pickup and delivery agent");
                    }else if(sysDingdan.getSfqrsh().equals("3")){
                        sysDingdan.setSfqrshname("In logistics transportation");
                    }else if(sysDingdan.getSfqrsh().equals("4")){
                        sysDingdan.setSfqrshname("Received items");
                    }else if(sysDingdan.getSfqrsh().equals("5")){
                        sysDingdan.setSfqrshname("Goods in transit");
                    }else if(sysDingdan.getSfqrsh().equals("6")){
                        sysDingdan.setSfqrshname("Returning items");
                    }else if(sysDingdan.getSfqrsh().equals("7")){
                        sysDingdan.setSfqrshname("The item has been returned to the shipper");
                    }
                }

                if(sysDingdan.getYhsfyfk() != null && sysDingdan.getYhsfyfk().equals("1")){
                    sysDingdan.setYhsfyfkname("Paid");
                }else{
                    sysDingdan.setYhsfyfkname("To be paid");
                }

            }
        }

        System.out.println("dingdanListdingdanListdingdanList"+dingdanList);
        return R.ok().put("dingdanList",dingdanList);
    }

    @Autowired
    private SysYszydService sysYszydService;

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/getMapInfo")
    public R getMapInfo(@RequestBody JSONObject param){
        System.out.println("param"+param);
        String id = param.getString("id");
        LinkedList<Map> pointArr = new LinkedList<>();

        SysDingdan sysDingdan = sysDingdanService.selectById(id);
        List<SysYszyd> yszyds = sysYszydService.selectList(new EntityWrapper<SysYszyd>().eq("dingdanid",sysDingdan.getId()).orderBy(true,"wldd",true));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = new HashMap();
        map.put("lng",sysDingdan.getQdhzbx());
        map.put("lat",sysDingdan.getQdhzby());
        map.put("name","Logistics starting point");
        System.out.println("getKssjgetKssjgetKssj"+sysDingdan.getKssj());
        map.put("wldd",format.format(sysDingdan.getKssj()));
        pointArr.add(map);
        if(yszyds.size()>0){
            for (int i = 0; i < yszyds.size(); i++) {
                SysYszyd sysYszyd = yszyds.get(i);
                Map mapchild = new HashMap();
                mapchild.put("lng",sysYszyd.getZydx());
                mapchild.put("lat",sysYszyd.getZydy());
                SysDept sysDept = sysDeptService.selectOne(new EntityWrapper<SysDept>().eq("dept_id",sysYszyd.getDeptid()));
                mapchild.put("name",sysDept.getName()+sysYszyd.getName());
                mapchild.put("wldd",format.format(sysYszyd.getWldd()));
                pointArr.add(mapchild);
            }
        }

        if(sysDingdan.getJssj() == null){

        }else{
            Map mapzd = new HashMap();
            mapzd.put("lng",sysDingdan.getShzbx());
            mapzd.put("lat",sysDingdan.getShzby());
            map.put("name","Logistics endpoint");
            map.put("wldd",format.format(sysDingdan.getJssj()));
            pointArr.add(mapzd);
        }

        String dqjdX="";
        String dqjdY="";
        if(sysDingdan.getJssj() != null){
            dqjdX=sysDingdan.getShzbx();
            dqjdY=sysDingdan.getShzby();
        }else {
            if(yszyds.size()>0){
                dqjdX=yszyds.get(yszyds.size()-1).getZydx();
                dqjdY=yszyds.get(yszyds.size()-1).getZydy();
            }

        }

        return R.ok().put("pointArr",pointArr).put("dqjdX",dqjdX).put("dqjdY",dqjdY).put("sysDingdan",sysDingdan);
    }

    @RequestMapping("/saveBgwlzt")
    public R saveBgwlzt(@RequestBody JSONObject param){
        String bgwlztid = param.getString("bgwlztid");
        String sfqrsh = param.getString("sfqrsh");
        SysDingdan sysDingdan = sysDingdanService.selectById(bgwlztid);
        sysDingdan.setSfqrsh(sfqrsh);
        if(sfqrsh.equals("4")){
            sysDingdan.setJssj(new Date());
        }
        sysDingdanService.updateById(sysDingdan);
        return R.ok();
    }

    @RequestMapping("/getUserByUsername")
    public R getUserByUsername(@RequestBody JSONObject param) {
        String username = param.getString("username");
        System.out.println("usernameusername"+username);
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username",username));
        System.out.println("pppppppppppppppppp"+sysUser);
        return R.ok().put("user",sysUser);
    }

    @RequestMapping("/setFkzt")
    public R setFkzt(@RequestBody JSONObject param) throws Exception {
        String  zxffid = param.getString("zxffid");
        SysDingdan sysDingdan = sysDingdanService.selectById(zxffid);



        sysDingdan.setYhsfyfk("1");
        sysDingdanService.updateById(sysDingdan);

        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username",sysDingdan.getYdry()));
        String text = "Dear "+sysUser.getRealname();
        if(sysUser.getEmail() != null){
            sendSimpleMail(text,sysUser.getEmail());
        }

        return R.ok();
    }


    public static String myEmailAccount = "244542721@qq.com";
    public static String myEmailPassword = "zknbyaitjswzbgfj";

    public static String myEmailSMTPHost = "smtp.qq.com";

    public void sendSimpleMail(String text,String receiveMailAccount) throws Exception {
        Properties props = new Properties();                    //
        props.setProperty("mail.transport.protocol", "smtp");   //
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   //
        props.setProperty("mail.smtp.auth", "true");            //

        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);


        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 //

        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,text);

        Transport transport = session.getTransport();

        transport.connect(myEmailAccount, myEmailPassword);

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String text) throws Exception {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sendMail, "Express logistics system", "UTF-8"));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX", "UTF-8"));

        message.setSubject(text, "UTF-8");

        message.setContent("You have successfully placed your order, please wait for the courier to pick it up.", "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        message.saveChanges();

        return message;
    }



}
