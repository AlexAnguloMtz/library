export enum Icons {
    add = 'add',
    export = 'export',
    user_avatar = 'user_avatar',
    book_open = 'book_open',
}

export const Icon: React.FC<{ name: Icons }> = ({ name }) => {
    if (name === Icons.add) {
        return (
            <svg width="19" height="19" viewBox="0 0 19 19" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M4.18774 9.35516H14.6877" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                <path d="M9.43774 4.10516V14.6052" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
            </svg>
        );
    }
    if (name === Icons.export) {
        return (
            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M8 10V2" stroke="#374151" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                <path d="M14 10V12.6667C14 13.0203 13.8595 13.3594 13.6095 13.6095C13.3594 13.8595 13.0203 14 12.6667 14H3.33333C2.97971 14 2.64057 13.8595 2.39052 13.6095C2.14048 13.3594 2 13.0203 2 12.6667V10" stroke="#374151" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                <path d="M4.66675 6.66669L8.00008 10L11.3334 6.66669" stroke="#374151" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
            </svg>

        );
    }
    if (name === Icons.user_avatar) {
        return (
            <svg width="20" height="21" viewBox="0 0 20 21" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M15.8333 18V16.3333C15.8333 15.4493 15.4821 14.6014 14.857 13.9763C14.2319 13.3512 13.384 13 12.5 13H7.49996C6.6159 13 5.76806 13.3512 5.14294 13.9763C4.51782 14.6014 4.16663 15.4493 4.16663 16.3333V18" stroke="#4F46E5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                <path d="M9.99996 9.66667C11.8409 9.66667 13.3333 8.17428 13.3333 6.33333C13.3333 4.49238 11.8409 3 9.99996 3C8.15901 3 6.66663 4.49238 6.66663 6.33333C6.66663 8.17428 8.15901 9.66667 9.99996 9.66667Z" stroke="#4F46E5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
            </svg>
        );
    }
    if (name === Icons.book_open) {
        return (
            <svg width="25" height="24" viewBox="0 0 25 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M12.4377 7V21" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                <path d="M21.4377 18C21.703 18 21.9573 17.8946 22.1449 17.7071C22.3324 17.5196 22.4377 17.2652 22.4377 17V4C22.4377 3.73478 22.3324 3.48043 22.1449 3.29289C21.9573 3.10536 21.703 3 21.4377 3H16.4377C15.3769 3 14.3595 3.42143 13.6093 4.17157C12.8592 4.92172 12.4377 5.93913 12.4377 7C12.4377 5.93913 12.0163 4.92172 11.2662 4.17157C10.516 3.42143 9.49861 3 8.43774 3H3.43774C3.17253 3 2.91817 3.10536 2.73064 3.29289C2.5431 3.48043 2.43774 3.73478 2.43774 4V17C2.43774 17.2652 2.5431 17.5196 2.73064 17.7071C2.91817 17.8946 3.17253 18 3.43774 18H9.43774C10.2334 18 10.9965 18.3161 11.5591 18.8787C12.1217 19.4413 12.4377 20.2044 12.4377 21C12.4377 20.2044 12.7538 19.4413 13.3164 18.8787C13.879 18.3161 14.6421 18 15.4377 18H21.4377Z" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
            </svg>
        );
    }
    throw new Error(`Icon not found: ${name}`);
};