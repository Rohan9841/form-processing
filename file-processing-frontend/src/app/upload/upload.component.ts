import { Component, OnInit } from '@angular/core';
import { UploadService } from 'src/app/services/upload.service';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  imageName: string;
  selectedFile: File;
  message: string;
  retrievedResponse: any;
  retrievedImage: any;
  base64Data: any;

  constructor(private uploadService: UploadService) { }

  ngOnInit(): void {

  }

  //Gets called when the user selects an image
  public onFileChanged(event) {
    //select file
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    console.log(this.selectedFile);

    //FormData API provides methods and properties to allow us easily 
    // prepare form data to be sent with POST HTTP requests
    const uploadImageData = new FormData();

    //In uploadImageData, we set key as 'imageFile' and value as 'selected file' and selected file name.
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.uploadService.upload(uploadImageData).subscribe(
      response => {
        this.message = "Image uploaded succesfully"
        console.log(response);
      },
      error=>{
        this.message = "Failed to upload";
        console.log(error);
      }
    );
  }

  getImage(){
    this.uploadService.getImage(this.imageName).subscribe(
      response=>{
        this.base64Data = response.data;
        this.retrievedImage = 'data:image/jpeg;base64,'+this.base64Data;
        console.log(response);
      }
    );
  }
  // selectedFiles: FileList;
  // currentFile: File;
  // progress = 0;
  // message = '';

  // fileInfos: Observable<any>;

  // constructor(private uploadService: UploadService) { }

  // ngOnInit(): void {
  //   // this.fileInfos = this.uploadService.getFiles();
  //   this.uploadService.getFiles().subscribe(
  //     response => {
  //       this.fileInfos = response;
  //     }
  //   )
  // }

  // // It helps us to get the selected Files.
  // selectFile(event) {
  //   this.selectedFiles = event.target.files;
  // }

  // // upload() method for upload file:
  // upload() {
  //   this.progress = 0;

  //   // We use selectedFiles for accessing current File as the first Item. 
  //   // Then we call uploadService.upload() method on the currentFile.
  //   this.currentFile = this.selectedFiles.item(0);
  //   this.uploadService.upload(this.currentFile).subscribe(
  //     event => {
  //       //        The progress will be calculated basing on event.loaded and event.total.
  //       //        If the transmission is done, the event will be a HttpResponse object. 
  //       //        At this time, we call uploadService.getFiles() to get the files’ information and assign the result to fileInfos variable.
  //       if (event.type === HttpEventType.UploadProgress) {
  //         this.progress = Math.round(100 * event.loaded / event.total);
  //       } else if (event instanceof HttpResponse) {
  //         console.log(event.body);
  //         this.message = event.body.message;
  //         this.uploadService.getFiles().subscribe(
  //           response => {
  //             this.fileInfos = response;
  //           }
  //         );
  //         this.currentFile = null;
  //       }
  //     },
  //     err => {
  //       this.progress = 0;
  //       this.message = 'Could not upload the file!';
  //       this.currentFile = undefined;
  //     });

  //   this.selectedFiles = undefined;
  // }

  // download(id: number) {
  //   console.log('clicked ' + id);
  //   this.uploadService.downloadFile(id).subscribe(
  //     (response: any) => {
  //       console.log(response);
  //       // let downloadLink = document.createElement('a');
  //       // downloadLink.href = window.URL.createObjectURL(new Blob([response.data], { type: response.type }));
  //       // if (response.name)
  //       //   downloadLink.setAttribute('download', response.name);
  //       // document.body.appendChild(downloadLink);
  //       // downloadLink.click();
  //       let blob = new Blob([response.data],{type: response.type});
  //       const url = window.URL.createObjectURL(blob);
  //       window.open(url);
  //     },
  //     error => {
  //       console.log(error);
  //     }
  //   );
  // }

  // saveAs(id:number){
  //   this.uploadService.downloadFile(id).subscribe(
  //     response=>{
  //       console.log(response);
  //       let blob = new Blob([response.data],{type: response.type});
  //       saveAs(blob, response.name);
  //     }
  //   )
  // }
}
