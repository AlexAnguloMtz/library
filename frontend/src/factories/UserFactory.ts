import type { RoleDTO } from "../models/RoleDTO";
import type { UserPreview } from "../models/UserPreview";

export class UserFactory {
    static createUsersPreviews(amount: number): UserPreview[] {
        const users: UserPreview[] = [];
        for (let i = 0; i < amount; i++) {
            users.push(this.createUserPreview());
        }
        return users;
    }

    private static createUserPreview(): UserPreview {
        const id = this.randomId();
        return {
            id,
            name: `User ${id}`,
            email: `user_${id}_@example.com`,
            roles: [this.makeRole()],
            activeLoans: Math.floor(Math.random() * 20).toString(),
            memberSince: new Date().toISOString().split('T')[0],
            phone: this.makePhoneNumber()
        };
    }

    private static randomId(): string {
        return Math.floor(Math.random() * 10000).toString();
    }

    private static makePhoneNumber(): string {
        const areaCode = Math.floor(Math.random() * 900) + 100;
        const centralOfficeCode = Math.floor(Math.random() * 900) + 100;
        const lineNumber = Math.floor(Math.random() * 9000) + 1000;
        return `${areaCode}-${centralOfficeCode}-${lineNumber}`;
    }

    private static makeRole(): RoleDTO {
        const roles = [
            { id: "1", name: 'Admin', slug: "admin", createdAt: new Date().toISOString(), updatedAt: new Date().toISOString() },
            { id: "2", name: 'Usuario', slug: "user", createdAt: new Date().toISOString(), updatedAt: new Date().toISOString() },
            { id: "3", name: 'Personal', slug: "staff", createdAt: new Date().toISOString(), updatedAt: new Date().toISOString() }
        ];
        return roles[Math.floor(Math.random() * roles.length)];
    }
}