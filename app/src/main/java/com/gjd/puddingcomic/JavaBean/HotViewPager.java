package com.gjd.puddingcomic.JavaBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class HotViewPager {


    /**
     * code : 200
     * data : {"banner_group":[{"pic":"http://i.kuaikanmanhua.com/image/160619/dvubtiyvl.webp-w640","title":"","type":3,"value":"13116"},{"pic":"http://i.kuaikanmanhua.com/image/160625/pzify3ep9.webp-w640","title":"","type":3,"value":"12145"},{"pic":"http://i.kuaikanmanhua.com/image/160625/vxxn63mrb.webp-w640","title":"","type":3,"value":"12759"},{"pic":"http://i.kuaikanmanhua.com/image/160625/oyiphjwg4.webp-w640","title":"","type":3,"value":"8558"},{"pic":"http://i.kuaikanmanhua.com/image/160625/2jrpcxmt6.webp-w640","title":"","type":3,"value":"12134"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * pic : http://i.kuaikanmanhua.com/image/160619/dvubtiyvl.webp-w640
         * title :
         * type : 3
         * value : 13116
         */

        private List<BannerGroupBean> banner_group;

        public List<BannerGroupBean> getBanner_group() {
            return banner_group;
        }

        public void setBanner_group(List<BannerGroupBean> banner_group) {
            this.banner_group = banner_group;
        }

        public static class BannerGroupBean {
            private String pic;
            private String title;
            private int type;
            private String value;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
