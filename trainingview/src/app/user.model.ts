export class UserModel {


  firstName: string = '';

  lastName: string = '';
  email: string = '';

  age: number = 0;


  constructor(firstName: string, lastName: string, email: string, age: number) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }
}
