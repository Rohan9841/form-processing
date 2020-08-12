import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  private baseUrl = "http://localhost:8081";
  constructor(private http: HttpClient) { }

  upload(uploadImageData:FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/files/upload`, uploadImageData);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }

  getImage(imageName: string): any{
    return this.http.get(`${this.baseUrl}/files/${imageName}`);
    
  }
}
