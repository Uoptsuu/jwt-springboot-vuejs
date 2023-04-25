<template>
    <form @submit.prevent="login" class="form" style="margin: auto; width: 20%; margin-top: 50px;">
        <h1 align=center>Login</h1>
        <div class="mb-3">
            <label for="username" class="form-label"><b>Username: </b></label>
            <input type="text" v-model="User.username" class="form-control" id="username" name="username" aria-describedby="">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><b>Password: </b></label>
            <input type="password" v-model="User.password" class="form-control" id="password" name="password" aria-describedby="">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <br>
        <br>
        <div class="alert alert-danger" role="alert" v-bind:style="{ display }">
            {{ message }}
        </div>
    </form>
</template>

<script>
    import axios from "axios"
    import UserService from '../service/UserService'
    export default {
        name :'Login',
        data(){
            return {
                User: {
                    username: '',
                    password: ''
                },
                display: 'none',
                message: ''
            }
        },
        methods: {
            login() {
                if (this.User.password != '' && this.User.username != '') {
                    if (!UserService.checkSpecialChar(this.User.password)) {
                        UserService.login(this.User) 
                        .then((res) => {
                            if(res.data.token != null && res.data.token != "") {
                                window.sessionStorage.clear();
                                window.sessionStorage.setItem("jwtToken", res.data.token);
                                window.sessionStorage.setItem("role", res.data.role);
                                axios.defaults.headers.Authorization = `Bearer ${res.data.token}`
                                if(res.data.role == "ROLE_ADMIN") {
                                    this.$router.push("/admin");
                                } else if(res.data.role == "ROLE_USER") {
                                    this.$router.push("/user");
                                }
                            }
                        })
                        .catch((err) => {
                            this.display = 'block';
                            this.message = "Thông tin không tồn tại.";})
                    } else {
                        this.display = 'block';
                        this.message = "Thông tin không được chứa các ký tự đặc biệt.";
                    }
                } else {
                    this.display = 'block';
                    this.message = "Thông tin trống.";
                }
            },
            checkLogin() {
                if(window.sessionStorage.getItem("jwtToken") != null && window.sessionStorage.getItem("jwtToken") != "") {
                    if(window.sessionStorage.getItem("role") == "ROLE_ADMIN") {
                        this.$router.push("/admin");
                    } else if(window.sessionStorage.getItem("role") == "ROLE_USER") {
                        this.$router.push("/user");
                    }
                }
            }
        },
        created() {
            this.checkLogin();
        }    
    }
</script>
  