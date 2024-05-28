import { PushQualif } from './../../../shared/models/prospect.model';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import {
  ChangeDetectionStrategy,
  Component,
  inject,
  OnInit,
  // signal,
} from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

import { Prospect } from '@eps/shared/models/prospect.model';
import { User } from '@eps/shared/models/user.model';
import { FileResponse } from '@eps/shared/models/file-response';

import { ProspectService } from '@eps/service/prospect/prospect.service';
import { FileService } from '@eps/service/file/file.service';
import { ConfirmationService, MessageService } from 'primeng/api';

import { BaseIcon } from 'primeng/baseicon';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { FileSelectEvent, FileUploadModule } from 'primeng/fileupload';
import { InputMaskModule } from 'primeng/inputmask';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';

interface expandedRows {
  [key: string]: boolean;
}

@Component({
  selector: 'eps-list-prospects',
  standalone: true,
  imports: [
    CalendarModule,
    CommonModule,
    ConfirmDialogModule,
    BaseIcon,
    ButtonModule,
    DialogModule,
    DropdownModule,
    FileUploadModule,
    FormsModule,
    InputTextModule,
    InputMaskModule,
    TableModule,
    TagModule,
    ToolbarModule,
    ToastModule,
    NgIf,
    NgFor,
  ],
  templateUrl: 'list-prospects.component.html',
  styleUrl: 'list-prospects.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [MessageService, ConfirmationService],
})
export class ListProspectsComponent implements OnInit {
   // services
  readonly prospectService = inject(ProspectService);
  readonly messageService = inject(MessageService);
  readonly fileService = inject(FileService);
  readonly confirmService = inject(ConfirmationService);

  // Table
  prospects: Prospect[] = [];
  clonedProspects: { [s: string]: Prospect } = {};
  editing = false;
  dateContact: Date = new Date();
  dateEntretien: Date = new Date();
  grille: string = '';
  dc: string = '';
  expandedRows: expandedRows = {};
  isExpanded: boolean = false;
  expandedEditing = false;

  // dialog
  visible: boolean = false;
  prospect!: Prospect;
  submitted: boolean = false;
  lastName: string = '';
  firstName: string = '';
  trigram: string = '';
  email: string = '';
  phone: number = 0;
  profil: string = '';
  cvFile: string = '';
  fileName: string = '';
  trigramExist: boolean = false;


  ngOnInit(): void {
    this.prospectService.getProspects().subscribe((prospects: Prospect[]) => {
      console.warn(prospects);
      this.prospects.push(...prospects);
    });
  }

  newProspect() {
    this.visible = true;
  }

  onSave(newProspect: NgForm) {
    if(newProspect.valid) {
      const newProspect: Prospect = {
        firstName: this.firstName,
        lastName: this.lastName,
        trigramme: this.trigram,
        email: this.email,
        phone: this.phone.toString(),
        profil: this.profil,
        cv: this.cvFile,
      };

      this.prospectService.saveProspect(newProspect).subscribe(() => {
        this.messageService.add({ severity:'success', summary: 'Success', detail: 'Le prospect est ajouté' });
        this.visible = false;
      });
    }
  }

  generateTrigram() {
    this.firstName && this.lastName ?
      this.trigram = (this.firstName.substring(0,1) + this.lastName.substring(0,1) + this.lastName.substring(this.lastName.length - 1)).toLowerCase() :
      this.trigram = '';
    if(this.trigram) {
      this.checkTrigram(this.trigram);
    }
  }

  checkTrigram(trigram: string) {
    this.prospectService.checkTrigram(trigram).subscribe(
      data => this.trigramExist = data,
      error => console.error(error)
    );
  }

  fullNameBum(data: User) {
    console.log(data);
    if(data ?? '') {
      return `${data.firstName} ${data.lastName}`;
    } else {
      return '';
    }
  }

  validatedBum(event: Event) {
    console.log(event);
    this.confirmService.confirm({
      target: event.target as EventTarget,
      message: 'Are you sure that you want to proceed?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      acceptIcon:"none",
      rejectIcon:"none",
      rejectButtonStyleClass:"p-button-text",
      accept: () => {
          this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted' });
      },
      reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected', life: 3000 });
      }
  });

  }

  onRowEdit(prospect: Prospect) {
    this.clonedProspects[prospect._id as string] = { ...prospect };
    this.editing = true;
  }

  onRowEditSave(prospect: Prospect) {
    console.log(prospect);
    if (prospect._id) {
      this.prospectService.updateProspect(prospect).subscribe(() => {
        this.messageService.add({ severity:'success', summary: 'Success', detail: 'Le prospect est modifié' });
      });
      delete this.clonedProspects[prospect._id as string];
    }
    this.editing = false;
  }

  onRowEditCancel(prospect: Prospect, index: number) {
    this.prospects[index] = this.clonedProspects[prospect._id as string];
    delete this.clonedProspects[prospect._id as string];
    this.editing = false;
  }

  archiveProspect(event: Event, prospect: Prospect) {
    this.editing = false;
    console.log(event.target, prospect._id);
    this.confirmService.confirm({
      target: event.target as EventTarget,
      message: 'Etès-vous sûre de vouloir archiver ce prospet ?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      acceptIcon:"none",
      rejectIcon:"none",
      rejectButtonStyleClass:"p-button-text",
      accept: () => {
          this.messageService.add({ severity: 'info', summary: 'Confirmation', detail: 'Vous avez archivé le prospect.' });
      },
      reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Rejet', detail: 'You have rejected', life: 3000 });
      }
  });

  }

  onUploadFile(event: FileSelectEvent, type: string) {
    const file: File = event.files[0];

    if(file) {
      this.fileService.uploadFile(file).subscribe((fileID: FileResponse) => {
        if(fileID && fileID.fileId) {
          switch(type) {
            case 'cv':
              this.cvFile = fileID.fileId;
              break;
            case 'grille':
              this.grille = fileID.fileId;
              break;
            case 'dc':
              this.dc = fileID.fileId;
              break;
          }
          this.messageService.add({ severity: 'info', summary: '', detail: 'Fichier téléchargé' });
        }
      },
      () => {
        this.messageService.add({ severity: 'error', summary: 'Une erreur est survenu', detail: 'Le fichier n\'a pas été téléchargé' });
      });
    }
  }

  expandAll() {
    if (!this.isExpanded) {
      this.prospects.forEach(prospect => prospect && prospect.pushQualif ? this.expandedRows[prospect.email] = true : '');
    } else {
      this.expandedRows = {};
    }
    this.isExpanded = !this.isExpanded;
  }

  onRowExpandedEdit() {
    this.expandedEditing = true;
  }
  onRowExpandedEditSave() {
    this.expandedEditing = false;
  }
  onRowExpandedEditCancel(pushQualif: PushQualif, index: number) {
    console.log(pushQualif, index);
    this.expandedEditing = false;
  }
}
