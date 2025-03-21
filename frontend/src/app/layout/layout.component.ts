import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../slidebar/slidebar.component';
import { ToolbarComponent } from '../components/toolbar/toolbar.component';



@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ToolbarComponent, SidebarComponent],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  constructor(private router: Router) { }

  isAuthPage(): boolean {
    return this.router.url.includes('/login') || this.router.url.includes('/register');
  }
}
