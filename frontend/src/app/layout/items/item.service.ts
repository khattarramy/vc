import { Observable } from "rxjs/Rx";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import "rxjs/Rx";
import { Item } from "./item.model";
import { Http, Response, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class ItemService {
  itemsChanged = new Subject<Item[]>();

  private items: Item[];

  constructor(private http: Http) {}

  getItems(): Observable<Item[]> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/items")
      .map((response: Response) => {
        const items: Item[] = response.json();
        return items;
      });
  }

  getItem(index: Number): Observable<Item> {
    return this.http
      .get("http://localhost:8080/valeurc/glg/items/" + index)
      .map((response: Response) => {
        const item: Item = response.json();
        return item;
      });
  }

  addItem(item: Item) {
    console.log(JSON.stringify(item));
    return this.http
      .post("http://localhost:8080/valeurc/glg/items", item)
      .map((response: Response) => {
        this.getItems().subscribe(response => {
          this.itemsChanged.next(response);
        });
      });
  }

  updateItem(index: Number, newItem: Item) {
    const x: string = "http://localhost:8080/valeurc/glg/items/" + index;
    console.log(x);
    return this.http.put(x, newItem).map((response: Response) => {
      this.getItems().subscribe(response => {
        this.itemsChanged.next(response);
      });
    });
  }

  deleteItem(index: Number) {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/items/" + index)
      .map((response: Response) => {
        this.getItems().subscribe(response => {
          this.itemsChanged.next(response);
        });
      });
  }

  deleteItems() {
    return this.http
      .delete("http://localhost:8080/valeurc/glg/items");
  }
}
