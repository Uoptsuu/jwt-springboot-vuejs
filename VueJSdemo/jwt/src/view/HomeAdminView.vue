<script>
import {RouterLink, RouterView} from 'vue-router'
import HomeAdmin from '../components/HomeAdmin.vue'
import Header from '../components/layout/Header.vue'
export default {
    name:"HomeAdminView",
    components: {
        Header,
        HomeAdmin
    }
}
</script>

<template>
    <Header/>
    <HomeAdmin/>
</template>