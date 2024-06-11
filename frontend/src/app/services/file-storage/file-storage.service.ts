import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "@eps/env/environment.dev";
import { FileResponse } from "@eps/shared/models/file-response";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FileStorageService {
  readonly #http = inject(HttpClient);

  apiUrl = `${environment.apiUrl}/files`;

  // POST: **/upload**
  uploadFile(file: File): Observable<FileResponse> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.#http.post<FileResponse>(`${this.apiUrl}/upload`, formData, {
      headers: new HttpHeaders({ 'Accept': 'application/json'})
    });
  }

  // GET: **/download**
  downloadFile(fileId: string): Observable<Blob> {
    return this.#http.get(`${this.apiUrl}/download/${fileId}`, { responseType: 'blob' });
  }
}
