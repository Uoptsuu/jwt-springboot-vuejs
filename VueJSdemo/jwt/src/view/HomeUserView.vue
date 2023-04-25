<script>
import {RouterLink, RouterView} from 'vue-router'
import HomeUser from '../components/HomeUser.vue'
import Header from '../components/layout/Header.vue'
import UserService from '../service/UserService.js'

export default {
    name:"HomeUserView",
    components: {
        Header,
        HomeUser
    },
    created() {
        UserService.checkLogin(this.$router);
    } 
}
</script>

<template>
    <Header/>
    <HomeUser/>
</template>