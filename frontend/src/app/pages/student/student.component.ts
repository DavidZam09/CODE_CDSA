import { Component } from '@angular/core';
import { StudentListComponent } from '../../components/student-list/student-list/student-list.component';

@Component({
  selector: 'app-student',
  imports: [StudentListComponent],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent {

}
