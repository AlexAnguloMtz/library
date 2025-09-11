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
            role: this.makeRole(),
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

    private static makeRole(): string {
        const roles = ['Admin', 'Usuario', 'Personal'];
        return roles[Math.floor(Math.random() * roles.length)];
    }
}
