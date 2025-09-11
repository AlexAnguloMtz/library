import React, { } from 'react';
import './styles.css';
import { DashboardModuleTopBar } from '../../components/DashboardModuleTopBar/DashboardModuleTopBar';

const Books: React.FC = () => {
    return (
        <div className='parent-container'>
            <DashboardModuleTopBar
                title="Libros"
                onExportClick={() => { }}
                onNewClick={() => { }}
            />
        </div>
    );
};

export default Books;
