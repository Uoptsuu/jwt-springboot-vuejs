import axios from 'axios'

class UserService{
    login(loginRequest) {
        var login = axios.create();
        delete login.defaults.headers.common['Authorization']
        return login.post(`/login`, loginRequest)
    }
    homePageUser() {
        return axios.get(`/user/home`)
    }
    getUsers(){
        return axios.get(`/admin/home`)
    }
    getDataForUpdate(user_id) {
        return axios.get(`/admin/update/${user_id}`)
    }
    getRoleForInsert() {
        return axios.get(`/admin/insert`)
    }
    insert(insertRequest) {
        return axios.post(`/admin/insert`, insertRequest)
    }
    update(updateRequest) {
        return axios.put(`/admin/update`, updateRequest)
    }
    delete(user_id) {
        return axios.delete(`/admin/delete/${user_id}`)
    }
    checkSpecialChar(input){
        const format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
        return format.test(input);
    }
    checkLogin(router) {
        if(window.sessionStorage.getItem("jwtToken") == null || window.sessionStorage.getItem("jwtToken") == "") {
            router.push("/");
        }
    }
}

export default new UserService()