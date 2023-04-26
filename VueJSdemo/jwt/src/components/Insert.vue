<template>
    <form @submit.prevent="insert()" style="width: 20%; margin: auto ;">
        <h4 class="display h-5" align=center>Insert</h4>
        <div class="mb-3">
            <label for="username" class="form-label"><b>Username: </b></label>
            <input type="text" v-model="User.username" class="form-control" id="username" name="username" aria-describedby="">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label"><b>Address: </b></label>
            <input type="text" v-model="User.address" class="form-control" id="address" name="address" aria-describedby="">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><b>Password: </b></label>
            <input type="text" v-model="User.password" class="form-control" id="password" name="password" aria-describedby="">
        </div>
        <div class="mb-3">
            <label for="" class="form-label"><b>Role: </b></label>
            <div v-for="role in Roles" v-bind:key="role.id">
                <input type="radio" v-model="User.roleId" name="roleId" :value="role.id" class="form-check-input">
                <label class="form-check-label">{{role.name}}</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
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
        data(){
            return {
                User: {
                    username: '',
                    password: '',
                    address: '',
                    roleId: ''
                },
                Roles: [],
                display:'none',
                message:''
            }
            
        },
        methods: {
            checkInsert(){
                let msg = "";
                console.log(this.User.username + this.User.address + this.User.password + this.User.roleId);
                if (UserService.checkSpecialChar(this.User.username + this.User.address + this.User.password + this.User.roleId)){
                    msg += "không được chứa các ký tự đặc biệt";
                } 
                if (this.User.address == "" || this.User.username == "" || this.User.roleId == "" || this.User.password == "") {
                    if (msg != "") msg += " và ";
                    msg += "không được bỏ trống";
                }
                if (msg != "") msg = "Thông tin " + msg + ".";
                return msg;
            },
            setRole(){
                UserService.getRoleForInsert().then((res => {
                    console.log(res)
                    this.Roles = res.data.listRole;
                }))
            },
            insert(){
                if (this.checkInsert() == "") {
                    //console.log(this.User);
                    //console.log(this.checkUpdate());
                    UserService.insert(this.User) 
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
                    this.message = this.checkInsert();
                }
            }
        },
        created() {
            this.setRole();
        }    
    }
</script>
  