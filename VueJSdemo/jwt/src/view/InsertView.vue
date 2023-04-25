<script>
import {RouterLink, RouterView} from 'vue-router'
import Insert from '../components/Insert.vue'
import Header from '../components/layout/Header.vue'
export default {
    name:"InsertView",
    components: {
        Header,
        Insert
    }
}
</script>

<template>
    <Header/>
    <Insert/>
</template>