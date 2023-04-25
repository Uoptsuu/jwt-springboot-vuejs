<script>
import {RouterLink, RouterView} from 'vue-router'
import Update from '../components/Update.vue'
import Header from '../components/layout/Header.vue'
export default {
    name:"UpdateView",
    components: {
        Header,
        Update
    }
}
</script>

<template>
    <Header/>
    <Update/>
</template>