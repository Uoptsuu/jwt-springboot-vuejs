<template style="margin-bottom: 50px;">
    <hr>
    <a href="" @click="logout()" class="btn btn-danger" style="margin-left: 90%;">Logout</a>
    <hr>
</template>

<script>
    export default {
        name :'Logout',
        methods: {
            logout(){
                window.sessionStorage.clear();
                window.sessionStorage.removeItem("jwtToken");
                window.sessionStorage.removeItem("role");
                this.$router.push("/");
            }
        },
        created() {
        }    
    }
</script>
  