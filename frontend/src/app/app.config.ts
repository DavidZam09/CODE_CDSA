import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { withInterceptorsFromDi, provideHttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';

// 📌 Importar módulos de Angular Material
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { LayoutComponent } from './layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { StudentComponent } from './pages/student/student.component';
import { ProfesorComponent } from './pages/profesor/profesor.component';
import { AdmonComponent } from './pages/admon/admon.component';

import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';

import { AuthInterceptor } from './services/auth.interceptor';
import { authGuard } from './guards/auth.guard';
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent, canActivate: [authGuard] },
      { path: 'student', component: StudentComponent, canActivate: [authGuard] },
      { path: 'profesor', component: ProfesorComponent, canActivate: [authGuard] },
      { path: 'admon', component: AdmonComponent, canActivate: [authGuard] },
    ]
  },
  { path: '**', redirectTo: 'login' }
];

export const appConfig: ApplicationConfig = {
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimations(),
    FormsModule,
    importProvidersFrom(
      MatDialogModule,
      MatButtonModule,
      MatFormFieldModule,
      MatInputModule
    ),
  ],
};
