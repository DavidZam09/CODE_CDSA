import { Component } from '@angular/core';
import { AuthServiceService } from '../../services/auth-service.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user = { username: '', password: '', firstname: '', lastname: '', country: '' };

  constructor(private authService: AuthServiceService, private router: Router) { }

  register() {
    this.authService.register(this.user).subscribe(
      response => {
        this.authService.setToken(response.token);
        this.router.navigate(['/login']);
      },
      error => console.error('Registration failed', error)
    );
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }
}
