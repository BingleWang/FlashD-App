package cn.jeefast.common.utils;

/**
 *
 */
public class Constant {
	public static final int SUPER_ADMIN = 1;

	/**
	 * menutype
	 * 
	 */
    public enum MenuType {
        /**
         * directory
         */
    	CATALOG(0),
        /**
         * menu
         */
        MENU(1),
        /**
         * button
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     *
     * 
     */
    public enum ScheduleStatus {
        /**
         * normal
         */
    	NORMAL(0),
        /**
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     */
    public enum CloudService {
        /**
         */
        QINIU(1),
        /**
         */
        ALIYUN(2),
        /**
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
