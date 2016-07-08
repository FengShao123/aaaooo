package com.gjd.puddingcomic.JavaBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ShowComicsListActivityBean {



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
         * comics_count : 16
         * comments_count : 102504
         * cover_image_url : http://i.kuaikanmanhua.com/image/160612/yqr4c0lqy.webp-w640
         * created_at : 1465703869
         * description : 超级怕鬼却要去当阎王是怎样的一种体验？【授权/更新 责编：奶片侠】
         * discover_image_url : null
         * id : 848
         * is_favourite : false
         * label_id : 5
         * likes_count : 3628719
         * order : 100
         * title : 阎王不高兴
         * updated_at : 1465703869
         * user : {"avatar_url":"http://i.kuaikanmanhua.com/image/150421/i26y7dz2t.jpg-w180","grade":1,"id":6,"nickname":"使徒子","pub_feed":0,"reg_type":"weibo"}
         * vertical_image_url : http://i.kuaikanmanhua.com/image/160612/0debex0jz.webp-w320
         */
        /**
         * comics_count: 6,
         comments_count: 56143,
         cover_image_url: "http://i.kuaikanmanhua.com/image/160526/vta4p2nak.webp-w640",
         created_at: 1464229157,
         description: "云剑山庄的大小姐陆望心与皇子夏侯佑相爱，并以身相许，不料，登基后的夏侯佑竟把陆望心打入冷宫，并满门抄斩，绝望的陆望心却意外穿越回16岁，重获新生的陆望心从此踏上了复仇之路……【独家/每周五更新 责编：林早上】",
         discover_image_url: null,
         id: 834,
         is_favourite: false,
         label_id: 37,
         likes_count: 1662430,
         order: 200,
         title: "君与望心",
         updated_at: 1464229157,
         user: {
         avatar_url: "http://i.kuaikanmanhua.com/image/160526/tftou7pvt.webp-w180",
         grade: 1,
         id: 13219143,
         nickname: "左小翎/夏天岛+大仙/夏天岛",
         pub_feed: 0,
         reg_type: "author"
         },
         user_id: 13219143,
         vertical_image_url: "http://i.kuaikanmanhua.com/image/160526/8e4avlkjl.webp-w320"
         */

        private List<TopicsBean> topics;

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public static class TopicsBean {
            private int comics_count;
            private int comments_count;
            private String cover_image_url;
            private int created_at;
            private String description;
            private Object discover_image_url;
            private int id;
            private boolean is_favourite;
            private int label_id;
            private int likes_count;
            private int order;
            private String title;
            private int updated_at;
            /**
             * avatar_url : http://i.kuaikanmanhua.com/image/150421/i26y7dz2t.jpg-w180
             * grade : 1
             * id : 6
             * nickname : 使徒子
             * pub_feed : 0
             * reg_type : weibo
             */

            private UserBean user;
            private String vertical_image_url;

            public int getComics_count() {
                return comics_count;
            }

            public void setComics_count(int comics_count) {
                this.comics_count = comics_count;
            }

            public int getComments_count() {
                return comments_count;
            }

            public void setComments_count(int comments_count) {
                this.comments_count = comments_count;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getDiscover_image_url() {
                return discover_image_url;
            }

            public void setDiscover_image_url(Object discover_image_url) {
                this.discover_image_url = discover_image_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIs_favourite() {
                return is_favourite;
            }

            public void setIs_favourite(boolean is_favourite) {
                this.is_favourite = is_favourite;
            }

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getVertical_image_url() {
                return vertical_image_url;
            }

            public void setVertical_image_url(String vertical_image_url) {
                this.vertical_image_url = vertical_image_url;
            }

            public static class UserBean {
                private String avatar_url;
                private int grade;
                private int id;
                private String nickname;
                private int pub_feed;
                private String reg_type;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getPub_feed() {
                    return pub_feed;
                }

                public void setPub_feed(int pub_feed) {
                    this.pub_feed = pub_feed;
                }

                public String getReg_type() {
                    return reg_type;
                }

                public void setReg_type(String reg_type) {
                    this.reg_type = reg_type;
                }
            }
        }
    }
}
