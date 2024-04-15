import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor() { }

  async signIn(email: string, password: string) {
    const response = await axios.post(
      `/auth/signin`,
      {
        "email": email,
        "password": password
      }
    );

    return response.data;
  }

  async signUp(login: string, email: string, password: string) {
    const response = await axios.post(
      '/auth/signup',
      {
        "name": login,
        "email": email,
        "password": password
      }
    );

    return response.data;
  }
}
