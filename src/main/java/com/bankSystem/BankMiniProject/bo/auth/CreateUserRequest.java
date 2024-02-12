package com.bankSystem.BankMiniProject.bo.auth;

public class CreateUserRequest {
    private String password;
        private String name;
        private String email;
        private String phoneNumber;
        private String status ;
        private String username;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public java.lang.String getName() {
            return name;
        }

        public void setName(java.lang.String name) {
            this.name = name;
        }

        public java.lang.String getEmail() {
            return email;
        }

        public void setEmail(java.lang.String email) {
            this.email = email;
        }

        public java.lang.String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(java.lang.String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

