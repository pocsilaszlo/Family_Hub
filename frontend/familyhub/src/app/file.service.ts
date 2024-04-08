import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { AxiosService } from './axios.service';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private apiUrl = this.axiosService.url;

  constructor(private http: HttpClient, private axiosService : AxiosService) { }

  uploadFile(file: File, type : string): Observable<number> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.http.post(this.apiUrl + '/upload/' + type + "/" + window.localStorage.getItem("auth_id"), formData, {
      reportProgress: true,
      observe: 'events'
    }).pipe(
      map(event => this.getUploadProgress(event)),
    );
  }

  private getUploadProgress(event: any): number {
    if (event.type === HttpEventType.UploadProgress) {
      const percentDone = Math.round((event.loaded / event.total) * 100);
      return percentDone;
    }
    return 0;
  }


}
