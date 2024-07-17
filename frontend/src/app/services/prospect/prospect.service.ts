import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Personnal } from '@gtper/shared/models/personnal.model';
import { environment } from '@gtper/env/environment.dev';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonnalService {
  readonly #http = inject(HttpClient);

  apiUrl = `${environment.apiUrl}/personnal`;

  // GET: **/allPersonnals**
  public getPersonnals(): Observable<Personnal[]> {
    return this.#http.get<Personnal[]>(`${this.apiUrl}/all`);
  }

  // GET by id
  public getPersonnalById(id: number) {
    return this.#http.get<Personnal>(`${this.apiUrl}/personnal/${id}`);
  }

  // POST: **/save**
  public savePersonnal(personnal: Personnal) {
    return this.#http.post<Personnal>(`${this.apiUrl}/save`, personnal, {
      headers: new HttpHeaders({ 'Accept': 'application/json'})
    });
  }

  // PUT: **/id**
  public updatePersonnal(personnal: Personnal) {
  console.log(personnal, personnal._id);

    return this.#http.put<Personnal>(`${this.apiUrl}/${personnal._id}`, personnal);
  }

  // DELETE: **/delete**
  public deletePersonnal(id: string) {
    return this.#http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // GET: **/trigramme**
  checkTrigram(trigram: string): Observable<boolean> {
    const params = new HttpParams().set('trigramme', trigram);
    return this.#http.get<boolean>(`${this.apiUrl}/trigram`, {params});
  }
}
