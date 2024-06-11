import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Prospect } from '@eps/shared/models/prospect.model';
import { environment } from '@eps/env/environment.dev';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProspectService {
  readonly #http = inject(HttpClient);

  apiUrl = `${environment.apiUrl}/prospect`;

  // GET: **/allProspects**
  public getProspects(): Observable<Prospect[]> {
    return this.#http.get<Prospect[]>(`${this.apiUrl}/all`);
  }

  // GET by id
  public getProspectById(id: number) {
    return this.#http.get<Prospect>(`${this.apiUrl}/prospect/${id}`);
  }

  // POST: **/save**
  public saveProspect(prospect: Prospect) {
    return this.#http.post<Prospect>(`${this.apiUrl}/save`, prospect, {
      headers: new HttpHeaders({ 'Accept': 'application/json'})
    });
  }

  // PUT: **/id**
  public updateProspect(prospect: Prospect) {
  console.log(prospect, prospect._id);

    return this.#http.put<Prospect>(`${this.apiUrl}/${prospect._id}`, prospect);
  }

  // DELETE: **/delete**
  public deleteProspect(id: string) {
    return this.#http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // GET: **/trigramme**
  checkTrigram(trigram: string): Observable<boolean> {
    const params = new HttpParams().set('trigramme', trigram);
    return this.#http.get<boolean>(`${this.apiUrl}/trigram`, {params});
  }
}
