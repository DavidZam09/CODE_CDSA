import { ApplicationConfig } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { StudentComponent } from './pages/student/student.component';
import { ProfesorComponent } from './pages/profesor/profesor.component';
import { AdmonComponent } from './pages/admon/admon.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'student', component: StudentComponent },
      { path: 'profesor', component: ProfesorComponent },
      { path: 'admon', component: AdmonComponent },
      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]
  }
];

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes)]
};
