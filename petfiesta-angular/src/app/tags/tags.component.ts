import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {Tag} from "../entities/tag";
import {TagService} from "../services/tag.service";

@Component({
  selector: 'app-tags',
  standalone: true,
  imports: [RouterLink, NgFor],
  templateUrl: './tags.component.html',
  styleUrl: './tags.component.css'
})
export class TagsComponent {
    tags: Tag[] = [];

    constructor(private tagService: TagService) {}

    async ngOnInit() {
        this.tags = await this.tagService.getTags();
    }

    async deleteTag(id: number) {
        await this.tagService.deleteTag(id);
        await this.ngOnInit();
    }
}
