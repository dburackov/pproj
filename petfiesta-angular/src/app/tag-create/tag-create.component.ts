import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {Tag} from "../entities/tag";
import {TagService} from "../services/tag.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-tag-create',
  standalone: true,
  imports: [FormsModule, NgFor],
  templateUrl: './tag-create.component.html',
  styleUrl: './tag-create.component.css'
})

export class TagCreateComponent {
    tag: Tag = new Tag();

    constructor(private tagService: TagService, private router: Router) {}

    async createTag() {
        await this.tagService.createTag(this.tag);
        await this.router.navigate(["/tags"]);
    }
}
