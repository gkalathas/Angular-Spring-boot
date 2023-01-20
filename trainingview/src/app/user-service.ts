import {Injectable} from '@angular/core';
import {UserModel} from './user.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService  {

  // user: UserModel =  new UserModel('George', 'kal', 'kal@gmail.com', 3231);
  // user1: UserModel =  new UserModel('Mpampis', 'kal', 'kal@gmail.com', 3231);
  // user2: UserModel =  new UserModel('kostas', 'kal', 'kal@gmail.com', 3231);
  // user3: UserModel =  new UserModel('leontas', 'kal', 'kal@gmail.com', 3231);
  // user4: UserModel =  new UserModel('despoina', 'kal', 'kal@gmail.com', 3231);
  // user5: UserModel =  new UserModel('sougias', 'kal', 'kal@gmail.com', 3231);
  users: UserModel[] = [new UserModel('George', 'kal', 'kal@gmail.com', 3231),
  new UserModel('Mpampis', 'kal', 'kal@gmail.com', 3231),
  new UserModel('kostas', 'kal', 'kal@gmail.com', 3231),
  new UserModel('leontas', 'kal', 'kal@gmail.com', 3231),
  new UserModel('despoina', 'kal', 'kal@gmail.com', 3231),
  new UserModel('sougias', 'kal', 'kal@gmail.com', 3231)];

 

 // getAllUsers() {
 //    return this.users;
 //  }


  // private baseUrl = "http://localhost:8080/api/v1/employees";
  constructor(private httpClient: HttpClient) { }

  // getEmployeesList(): Observable<UserModel[]>{
  //   return this.httpClient.get<UserModel[]>(`${this.baseUrl}`);
  // }
  //
  // createEmployee(employee: UserModel): Observable<Object> {
  //   return this.httpClient.post(`${this.baseUrl}`, employee);
  // }
  //
  // getEmployeeById(id: number): Observable<UserModel> {
  //   return this.httpClient.get<UserModel>(`${this.baseUrl}/${id}`)
  // }
  //
  // updateEmployee(id: number, employee: UserModel): Observable<Object> {
  //   return this.httpClient.put(`${this.baseUrl}/${id}`, employee);
  // }
  //
  // deleteEmployee(id: number): Observable<object> {
  //   return this.httpClient.delete(`${this.baseUrl}/${id}`);
  // }
  // getEmployeeByEmailId(emailId: any): Observable<UserModel[]> {
  //   return this.httpClient.get<UserModel[]>(`${this.baseUrl}?emailId=${emailId}`);
  // }

}
