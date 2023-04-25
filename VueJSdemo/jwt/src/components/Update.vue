<template>
    <form @submit.prevent="update" style="width: 20%; margin: auto ;">
        <h4 class="display h-5" align=center>Update</h4>
        <input type="hidden" name="id" v-model="User.id">
        <div class="mb-3">
            <label for="username" class="form-label"><b>Username: </b></label>
            <input type="text" v-model="User.username" class="form-control" id="username" name="username" aria-describedby="">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label"><b>Address: </b></label>
            <input type="text" v-model="User.address" class="form-control" id="address" name="address" aria-describedby="">
        </div>
        <div class="mb-3">
            <label for="" class="form-label"><b>Role: </b></label>
            <div v-for="role in Roles" v-bind:key="role.id">
                <input type="radio" v-model="User.role" name="roleId" :value="role.id" class="form-check-input">
                <label class="form-check-label">{{role.name}}</label>
            </div>
        </div>
        <div class="mb-3">
            <label for="" class="form-label"><b>Active: </b></label>
            <div>
                <input type="radio" v-model="User.active" name="active" value="true" class="form-check-input">
                <label class="form-check-label">Yes</label>
                <br>
                <input type="radio" v-model="User.active" name="active" value="false" class="form-check-input">
                <label class="form-check-label">No</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <br>
        <br>
        <div class="alert alert-danger" role="alert" v-bind:style="{ display }">
            {{ message }}
        </div>
    </form>
</template>

<script>
    import UserService from '../service/UserService.js'
    export default {
        name :'Update',
        data(){
            return {
                User: {
                    id:'',
                    username: '',
                    address: '',
                    role: '',
                    active: ''
                },
                Roles: [],
                display: 'none',
                message: ''
            }
            
        },
        methods: {
            checkUpdate(){
                let msg = "";
                if (UserService.checkSpecialChar(this.User.username + this.User.address + this.User.id + this.User.role + this.User.active)){
                    msg += "không được chứa các ký tự đặc biệt";
                } 
                if (this.User.address == "" || this.User.username == "" || this.User.id == "") {
                    if (msg != "") msg += " và ";
                    msg += "không được bỏ trống";
                }
                if (msg != "") msg = "Thông tin " + msg + ".";
                return msg;
            },
            setData(){
                UserService.getDataForUpdate(this.$route.params.id).then((res => {
                    //console.log(res);
                    this.Roles = res.data.listRole;
                    this.User = res.data.user;
                }))
            },
            update(){
                if (this.checkUpdate() == "") {
                    //console.log(this.User);
                    //console.log(this.checkUpdate());
                    UserService.update(this.User) 
                        .then(() => {
                            sessionStorage.setItem('change',true);
                            sessionStorage.setItem('msg','Thành công!');
                            this.$router.push('/admin');
                        })
                    .catch((err) => {
                        this.display = 'block';
                        this.message = "Cập nhật thất bại. Error: " + err;
                    })
                } else {
                    this.display = 'block';
                    this.message = this.checkUpdate();
                }
            }
        },
        created() {
            UserService.checkLogin();
            this.setData();
        }    
    }
</script>
  