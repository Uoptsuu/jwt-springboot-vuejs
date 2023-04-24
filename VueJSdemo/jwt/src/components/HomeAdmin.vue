<template>
    <div v-for="user in Users" v-bind:key="user.id">
        <label for="username"><b>Username: </b></label>
        <span id="username" name="username">{{ user.id }}</span>
        <br>
        <br>
        <label for="role"><b>Role: </b></label>
        <span id="role" name="role">{{ user.role }}</span>
        <br>
        <br>
        <label for="address"><b>Address: </b></label>
        <span id="address" name="address">{{ user.address }}</span>
        <br>
        <br>
        <label for="active"><b>Active: </b></label>
        <span id="active" name="active">{{ user.active }}</span>
        <br>
        <br>
        <a :href="'/admin/update/' + user.id"><button>Update</button></a>
        <a href="" @click="delete(user.id)"><button>Delete</button></a>
        <hr>
    </div>
</template>
  
<script>
    import UserService from '../service/UserService.js'
    export default {
        name :'HomeAdmin',
        data(){
            return {
                Users: []
            }
        },
        methods: {
            getUsers(){
                UserService.getUsers().then((res => {
                    console.log(res);
                    this.Users = res.data;
                }))
            },
  
            delete(id){
                console.log(id)
                UserService.delete(id).then(() => {
                  alert("Deleted.");
                  this.getUsers();
                })
                .catch((err) => {console.log(err)});
            }
        },
        created() {
            //UserService.checkLogin;
            this.getUsers();
        }    
    }
</script>

  