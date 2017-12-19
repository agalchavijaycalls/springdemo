import * as React from 'react';
import * as TodoActions from '../../actions/todos';
import { RouteComponentProps } from 'react-router-dom';
import { connect } from 'react-redux';
import { Header } from '../../components/Header';
import { MainSection } from '../../components/MainSection';
import { RootState } from '../../reducers';
import { bindActionCreators } from 'redux';

export namespace App {
    export interface Props extends RouteComponentProps<void> {
        todos: TodoItemData[];
        actions: typeof TodoActions;
    }

    export interface State {
        /* empty */
    }
}

@connect(mapStateToProps, mapDispatchToProps)
export class App extends React.Component<App.Props, App.State> {

    render() {
        const {todos, actions, children} = this.props;
        return (
            <div className={''}>
                <Header addTodo={actions.addTodo}/>
                <MainSection todos={todos} actions={actions}/>
                {children}
            </div>
        );
    }
}

function mapStateToProps(state: RootState) {
    return {
        todos: state.todos
    };
}

function mapDispatchToProps(dispatch: any) {
    return {
        actions: bindActionCreators(TodoActions as any, dispatch)
    };
}
