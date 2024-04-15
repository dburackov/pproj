import { Component } from '@angular/core';
import { AuthorizationService } from '../services/authorization.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-signup',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './signup.component.html',
    styleUrl: './signup.component.css'
})
export class SignupComponent {
    login = '';
    email = '';
    password = '';
    constructor(private authorizationService: AuthorizationService) {}

    async signUp() {
        await this.authorizationService.signUp(this.login, this.email, this.password);
        window.location.href = '/pet-profiles';
    }
}
