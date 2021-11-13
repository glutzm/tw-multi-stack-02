import React from "react";
import {PageTitleContainer, PageTitleStyled, PageSubtitleStyled} from "@components/data-display/PageTitle/PageTitle.style";

interface PageTitleProps {
    title: string;
    subtitle?: string | JSX.Element;
}

const PageTitle: React.FC<PageTitleProps> = (props) => {
    return (
        <PageTitleContainer>
            <PageTitleStyled>
                {props.title}
            </PageTitleStyled>
            {props.subtitle}
        </PageTitleContainer>
    );
}

export default PageTitle;
