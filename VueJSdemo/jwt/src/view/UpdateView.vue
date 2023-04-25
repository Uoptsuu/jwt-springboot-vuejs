<script>
import {RouterLink, RouterView} from 'vue-router'
import Update from '../components/Update.vue'
import Header from '../components/layout/Header.vue'
import UserService from '../service/UserService.js'

export default {
    name:"UpdateView",
    components: {
        Header,
        Update
    },
    created() {
        UserService.checkLogin(this.$router);
    } 
}
</script>

<template>
    <Header/>
    <Update/>
</template>