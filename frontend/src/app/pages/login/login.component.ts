import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from '../../services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private authService: AuthServiceService, private router: Router) { }

  login() {
    this.authService.login({ username: this.username, password: this.password }).subscribe(
      response => {
        this.authService.setToken(response.token);
        this.router.navigate(['/home']);
      },
      error => console.error('Login failed', error)
    );
  }
}
