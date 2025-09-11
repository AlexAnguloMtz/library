import React, { } from 'react';
import './styles.css';
import { DashboardModuleTopBar } from '../../components/DashboardModuleTopBar/DashboardModuleTopBar';

const Overview: React.FC = () => {
    return (
        <div className='parent-container'>
            <DashboardModuleTopBar
                title="General"
                onExportClick={() => { }}
                onNewClick={() => { }}
            />
        </div>
    );
};

export default Overview;
