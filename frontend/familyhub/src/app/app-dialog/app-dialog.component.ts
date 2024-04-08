import { CUSTOM_ELEMENTS_SCHEMA, Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatDialogActions } from '@angular/material/dialog';
import { App } from '../applications/app';



@Component({
  selector: 'app-app-dialog',
  templateUrl: './app-dialog.component.html',
  styleUrl: './app-dialog.component.scss',
})
export class AppDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: App) {}
}
