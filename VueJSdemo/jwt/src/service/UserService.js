import axios from 'axios'

const USER_API_BASE_URL = 'http://localhost:8080/api/auth'
class UserService{
    login(loginRequest){
        return axios.post(USER_API_BASE_URL + `/login`, loginRequest);
    }
    homePageUser(){
        return axios.get(USER_API_BASE_URL + `/user/home`);
    }
    getUsers(){
        return axios.get(USER_API_BASE_URL + `/admin/home`);
    }
    getDataForUpdate(user_id){
        return axios.get(USER_API_BASE_URL + `/admin/update/${user_id}`)
    }
    getRoleForInsert(){
        return axios.get(USER_API_BASE_URL + `/admin/insert`)
    }
    update(updateRequest) {
        return axios.put(USER_API_BASE_URL + `/admin/update`, updateRequest)
    }
    delete(user_id){
        return axios.delete(USER_API_BASE_URL + `/admin/delete/${user_id}`)
    }
}

export default new UserService()