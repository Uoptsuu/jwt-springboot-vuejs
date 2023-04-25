<script>
import {RouterLink, RouterView} from 'vue-router'
import HomeAdmin from '../components/HomeAdmin.vue'
import Header from '../components/layout/Header.vue'
import UserService from '../service/UserService.js'

export default {
    name:"HomeAdminView",
    components: {
        Header,
        HomeAdmin
    },
    created() {
        UserService.checkLogin(this.$router);
    } 
}
</script>

<template>
    <Header/>
    <HomeAdmin/>
</template>