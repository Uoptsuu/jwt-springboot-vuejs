<template>
    <a href="" @click="logout()"><button style="background-color: red;">Logout</button></a>
    <hr>
</template>

<script>
    export default {
        name :'Logout',
        methods: {
            logout(){
                window.localStorage.removeItem("jwtToken");
                window.localStorage.removeItem("role");
                this.$router.push("/");
            }
        },
        created() {
        }    
    }
</script>
  