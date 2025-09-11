import React, { } from 'react';
import './styles.css';
import { DashboardModuleTopBar } from '../../components/DashboardModuleTopBar/DashboardModuleTopBar';

const Authors: React.FC = () => {
    return (
        <div className='parent-container'>
            <DashboardModuleTopBar
                title="Autores"
                onExportClick={() => { }}
                onNewClick={() => { }}
            />
        </div>
    );
};

export default Authors;
