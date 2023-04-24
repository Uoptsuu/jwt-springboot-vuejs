<template>
    <form @submit.prevent="login">
        <label>Username: </label>
        <input type="text" v-model="User.username" name="username">
        <br>
        <br>
        <label>Password: </label>
        <input type="password" v-model="User.password" name="password">
        <br>
        <br>
        <button type="submit">Login</button>
    </form>
</template>

<script>
    import UserService from '../service/UserService'
    export default {
        name :'Login',
        data(){
            return {
                User: {
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            async login() {
                console.log(this.User)
                UserService.login(this.User) 
                .then((res) => {
                    if(res.data.token != null || res.data.token != "") {
                        window.localStorage.clear();
                        window.localStorage.setItem("jwtToken", res.data.token);
                        window.localStorage.setItem("role", res.data.role);
                        if(res.data.role == "ROLE_ADMIN") {
                            this.$router.push("/admin");
                        } else if(res.data.role == "ROLE_USER") {
                            this.$router.push("/user");
                        }
                    }
                })
                //.catch((err) => {console.log(err)})
            }
            // checkLogin() {
            //     if(window.localStorage.getItem("jwtToken") != null && window.localStorage.getItem("jwtToken") != "") {
            //         if(window.localStorage.getItem("role") == "ROLE_ADMIN") {
            //             this.$router.push("/admin");
            //         } else if(window.localStorage.getItem("role") == "ROLE_USER") {
            //             this.$router.push("/user");
            //         }
            //     }
            // }
        },
        created() {
            //this.checkLogin;
        }    
    }
</script>
  