import { CommonModule, NgFor, NgIf } from '@angular/common';
import {
  ChangeDetectionStrategy,
  Component,
  inject,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

import { newPersonnal, Personnal, PushQualif } from '@gtper/shared/models/personnal.model';
import { User } from '@gtper/shared/models/user.model';
import { FileResponse } from '@gtper/shared/models/file-response';

import { PersonnalService } from '@gtper/services/personnal/personnal.service';
import { FileStorageService } from '@gtper/services/file-storage/file-storage.service';
import { ConfirmationService, MessageService, PrimeNGConfig, Translation } from 'primeng/api';

import { BaseIcon } from 'primeng/baseicon';
import { ButtonModule } from 'primeng/button';
import { CalendarModule, LocaleSettings } from 'primeng/calendar';
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
import { TranslateModule, TranslateService } from '@ngx-translate/core';

interface expandedRows {
  [key: string]: boolean;
}

interface confirMessage {
  target: EventTarget;
  message: string;
  header: string,
  rejectButtonStyleClass: string;
  acceptSeverity: string;
  acceptSummary: string;
  acceptDetail: string;
  rejectSeverity: string;
  rejectDetail: string;
}

@Component({
  selector: 'gtper-list-personnals',
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
    TranslateModule,
    NgIf,
    NgFor,
  ],
  templateUrl: 'list-personnals.component.html',
  styleUrl: 'list-personnals.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [MessageService, ConfirmationService],
})
export class ListPersonnalsComponent implements OnInit {
   // services
  readonly personnalService = inject(PersonnalService);
  readonly messageService = inject(MessageService);
  readonly fileService = inject(FileStorageService);
  readonly confirmService = inject(ConfirmationService);
  readonly translate = inject(TranslateService);
  readonly primengConfig = inject(PrimeNGConfig);

  @ViewChild('formPersonnal') formPersonnal!: NgForm;

  // Table
  personnals: Personnal[] = [];
  clonedPersonnals: { [s: string]: Personnal } = {};
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
  personnal!: Personnal;
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

  locale!: LocaleSettings;

  constructor() {
    this.translate.setDefaultLang('fr');
    this.translate.use('fr');

    this.translate.get('primeng.calendar').subscribe(res => {
      this.primengConfig.setTranslation(res as Translation);
      this.locale = {
        firstDayOfWeek: 1,
        dayNames: res.dayNames,
        dayNamesShort: res.dayNamesShort,
        dayNamesMin: res.dayNamesMin,
        monthNames: res.monthNames,
        monthNamesShort: res.monthNamesShort,
        today: res.today,
        clear: res.clear
      }
    });
  }

  ngOnInit(): void {
    this.personnalService.getPersonnals().subscribe((personnals: Personnal[]) => {
      // console.warn(personnals);
      this.personnals.push(...personnals);
    });
  }

  newPersonnal() {
    this.visible = true;
  }

  onSave() {
  const userId = localStorage.getItem('userId');
  const rhValue = userId ? userId : undefined;
    if(this.formPersonnal.valid) {
      const newPersonnal: newPersonnal = {
        firstName: this.firstName,
        lastName: this.lastName,
        trigramme: this.trigram,
        email: this.email,
        phone: this.phone.toString(),
        profil: this.profil,
        cv: this.cvFile,
        rh: rhValue
      };

      this.personnalService.savePersonnal(newPersonnal).subscribe(() => {
        this.messageService.add({ severity:'success', summary: 'Success', detail: 'Le personnal est ajouté' });
        this.visible = false;
      });
    }
  }

  generateTrigram() {
    this.firstName && this.lastName ?
      this.trigram = (this.firstName.substring(0,1) + this.lastName.substring(0,1) + this.lastName.substring(this.lastName.length - 1)).toLowerCase():
      this.trigram = '';
    if(this.trigram) {
      this.checkTrigram(this.trigram);
    }
  }

  checkTrigram(trigram: string) {
    this.personnalService.checkTrigram(trigram).subscribe(
      data => this.trigramExist = data,
      error => console.error(error)
    );
  }

  fullNameBum(data: User) {
    if(data ?? '') {
      return `${data.firstName} ${data.lastName}`;
    } else {
      return '';
    }
  }

  validatedBum(event: Event) {
    const data: confirMessage = {
      target: event.target as EventTarget,
      message: 'Souhaitez-vous valider ce propect ?',
      header: 'Confirmation',
      rejectButtonStyleClass:"p-button-text",
      acceptSeverity: 'success',
      acceptSummary: 'Confirmation',
      acceptDetail: 'GO pour le personnal',
      rejectSeverity: 'error',
      rejectDetail: 'Action rejeté.'
    };
    this.confirmMessage(data);
  }

  onRowEdit(personnal: Personnal) {
    this.clonedPersonnals[personnal._id as string] = { ...personnal };
    this.editing = true;
  }

  onRowEditSave(personnal: Personnal) {
    console.log(personnal);
    if (personnal._id) {
      this.personnalService.updatePersonnal(personnal).subscribe(() => {
        this.messageService.add({ severity:'success', summary: 'Success', detail: 'Le personnal est modifié' });
      });
      delete this.clonedPersonnals[personnal._id as string];
    }
    this.editing = false;
  }

  onRowEditCancel(personnal: Personnal, index: number) {
    this.personnals[index] = this.clonedPersonnals[personnal._id as string];
    delete this.clonedPersonnals[personnal._id as string];
    this.editing = false;
  }

  archivePersonnal(event: Event, personnal: Personnal) {
    this.editing = false;
    console.log(personnal);

    const data: confirMessage = {
      target: event.target as EventTarget,
      message: 'Etès-vous sûre de vouloir archiver ce prospet ?',
      header: 'Confirmation',
      rejectButtonStyleClass:"p-button-text",
      acceptSeverity: 'success',
      acceptSummary: 'Confirmation',
      acceptDetail: 'Vous avez archivé le personnal.',
      rejectSeverity: 'error',
      rejectDetail: 'Action rejeté'
    };
    this.confirmMessage(data);
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

  confirmMessage(data: confirMessage): void {
    this.confirmService.confirm({
      target: data.target,
      message: data.message,
      header: data.header,
      icon: 'pi pi-exclamation-triangle',
      acceptIcon: 'none',
      rejectIcon: 'none',
      rejectButtonStyleClass: data.rejectButtonStyleClass,
      accept: () => {
        this.messageService.add({ severity: data.acceptSeverity, summary: data.acceptSummary, detail: data.acceptDetail });
      },
      reject: () => {
        this.messageService.add({ severity: data.rejectSeverity, summary: '', detail: data.rejectDetail, life: 3000 });
      }
  });
  }

  expandAll() {
    if (!this.isExpanded) {
      this.personnals.forEach(personnal => personnal && personnal.pushQualif ? this.expandedRows[personnal.email] = true : '');
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

  // Méthode pour initialiser le calendrier avec la bonne localisation
  initializeCalendar(): void {
    const calendarElement = document.querySelector('.p-calendar');
    if (calendarElement) {
      calendarElement.setAttribute('locale', JSON.stringify(this.locale));
    }
  }
}
