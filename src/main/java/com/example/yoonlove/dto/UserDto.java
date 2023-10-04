package com.example.yoonlove.dto;

import lombok.*;

public class UserDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserLoginRequest{
        @Setter
        private String loginId;
        @Setter
        private String password;

        public static UserLoginRequestBuilder builder(){
            return new UserLoginRequestBuilder();
        }

        public static class UserLoginRequestBuilder{
            private String loginId;
            private String password;

            public UserLoginRequestBuilder loginId(String id){
                this.loginId=id;
                return this;
            }

            public UserLoginRequestBuilder password(String pwd){
                this.password=pwd;
                return this;
            }

            public UserLoginRequest build(){
                return new UserLoginRequest(this.loginId,this.password);
            }
        }
    }
}

