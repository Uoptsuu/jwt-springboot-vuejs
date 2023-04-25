<script>
import {RouterLink, RouterView} from 'vue-router'
import HomeUser from '../components/HomeUser.vue'
import Header from '../components/layout/Header.vue'
export default {
    name:"HomeUserView",
    components: {
        Header,
        HomeUser
    }
}
</script>

<template>
    <Header/>
    <HomeUser/>
</template>