import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { withInterceptorsFromDi, provideHttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';

// Import Angular Material modules
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
import { AuthGuard } from './guards/auth.guard';
import { CursoComponent } from './pages/curso/curso.component';
import { InscripcionComponent } from './pages/inscripcion/inscripcion.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
      { path: 'student', component: StudentComponent, canActivate: [AuthGuard] },
      { path: 'profesor', component: ProfesorComponent, canActivate: [AuthGuard] },
      { path: 'admon', component: AdmonComponent, canActivate: [AuthGuard] },
      { path: 'curso', component: CursoComponent, canActivate: [AuthGuard] },
      { path: 'inscripcion', component: InscripcionComponent, canActivate: [AuthGuard] },
      { path: '', redirectTo: 'login', pathMatch: 'full' }
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
