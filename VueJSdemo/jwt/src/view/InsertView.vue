<script>
import {RouterLink, RouterView} from 'vue-router'
import Insert from '../components/Insert.vue'
import Header from '../components/layout/Header.vue'
import UserService from '../service/UserService.js'

export default {
    name:"InsertView",
    components: {
        Header,
        Insert
    },
    created() {
        UserService.checkLogin(this.$router);
    } 
}
</script>

<template>
    <Header/>
    <Insert/>
</template>