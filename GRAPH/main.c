#include <stdio.h>
#include <stdlib.h>
/***ГРАФ***/
/*typedef struct
{
    size_t nume,numv;
    t_btree **vertex;
}t_graph;*/ //Через АВЛ деревья

//Узел графа
typedef struct t_graph_node
{
    struct t_graph_node *next;
    size_t indv;
}t_graph_node;

//Структура графа
typedef struct
{
    size_t numv /*количество вершин*/,nume/*количество рёбер*/;
    t_graph_node **vertex;
}t_graph;

//Узел списка
typedef struct list_node
{
    struct list_node *next;
    size_t val;
}t_list_node;

//Структура списка
typedef struct
{
    size_t size;
    t_list_node *head;
}t_list;

//Конструктор списка
t_list* list_new ()
{
    t_list *list;
    list=((t_list*)(malloc(sizeof(t_list))));
    list->size=0;
    list->head=NULL;//((t_list_node*)(malloc(sizeof(t_list_node))));
    return list;
}

//Деструктор списка
void list_del (t_list *list)
{
    t_list_node *node, *cur;
    node = list->head;
    while (node!=NULL)
    {
        cur = (node->next);
        free(node);
        node =(cur);
    }
    free(list);

}

//Вставка элемента в список
 void list_add_elem(t_list *list,int val)
{
    t_list_node *node;
    node=(t_list_node*)malloc(sizeof(t_list_node));
    node->next=list->head;
    list->head=node;
    node->val=val;
    ++(list->size);
}

//Вывод списка
void list_get(t_list *list)
{
    t_list_node *node;
    node=list->head;
    while(node !=NULL)
    {
        printf("%i\n",node->val);
        node=node->next;
    }
}

//Возвращаем указатель на голову списка
t_list_node* list_get_head(t_list *list)
{
    return (list->head);
}
//Возвращает размер списка
size_t list_get_size(t_list *list)
{
    return (list->size);
}

//Конструктор графа
t_graph* graph_new(size_t numv)
{
    int i;
    t_graph *graph;
    graph=(t_graph*)(malloc(sizeof(t_graph)));
    graph->vertex=(t_graph_node*)(malloc(sizeof(t_graph_node)*numv));
    graph->numv=numv;
    graph->nume=0;
    for (i=0;i<numv;++i)
    {
        graph->vertex[i]=NULL;
    }
    return graph;
}

//Деструктор графа
void graph_del(t_graph* graph)
{
    t_graph_node *node;
    t_graph_node* cur;
    int i;
    for (i=0;i<graph->numv;++i)
    {
        cur = graph -> vertex[i];
        while (cur!=NULL)
        {
         t_graph_node* buf;
         buf = cur -> next;
         free(cur);
         cur = buf;
        }
    }
    free(graph);
}

//Вставка элемента в граф
void graph_add_edge (t_graph *graph ,size_t i1,size_t i2)
{
    t_graph_node *node;
    if (graph_get(graph,i1,i2)==NULL)
    {
        node = (t_graph_node*)malloc(sizeof(t_graph_node));
        node->indv =i2;
        node->next=graph->vertex[i1];
        graph->vertex[i1]=node;

        node = (t_graph_node*)malloc(sizeof(t_graph_node));
        node->indv =i1;
        node->next=graph->vertex[i2];
        graph->vertex[i2]=node;

        (++graph->nume);//увеличиваем колличество ребёр
    }

}


//Удаление элемента из графа
int graph_del_edge(t_graph *graph,size_t i1,size_t i2)
{
    t_graph_node *node,*cur;
    if (graph_get(graph,i1,i2)!=NULL)
    {
        node=graph->vertex[i1];
        while (node !=NULL)
        {
            if ((node->indv)==i2)
            {
                cur->next=node->next;
                free(node);
                node=cur;
            }
            cur=node;
            node=node->next;
        }
        graph->vertex[i1]=node;

        node=graph->vertex[i2];
        while (node !=NULL)
        {
            if ((node->indv)==i1)
            {
                cur->next=node->next;
                free(node);
                node=cur;
            }
            cur=node;
            node=node->next;
        }
        graph->vertex[i1]=node;

    (--(graph->nume));
    }
    return (0);
}

//Проверка есть ли ребро между двумя вершинами в графе
int graph_get(t_graph *graph,size_t i1,size_t i2)
{
    t_graph_node *node;
    node=graph->vertex[i1];
    while (node !=NULL)
    {
        if (node->indv==i2)
        {
            return (1);
        }
        node=node->next;
    }
    return NULL;
}

//Проверка на связность и циклы, поиск в глубину
/*int graph_dfs(t_graph *graph, size_t *NUMC)
{
    char Mark[graph->numv];//Массив маркеров
    size_t cycle=0;//Есть ли циклы или нет
    size_t numc=0;
    size_t last=NULL;
    int i;
    for (i=0;i<(graph->numv);++i)
    {
        Mark[i]=0;
    }
    //Поиск в глубину
    void DFS (size_t indv,size_t last)
    {
        if (Mark[indv])
        {
            cycle=1;
            return cycle;
        }
        t_graph_node *node = graph->vertex[indv];
        Mark[indv]=1;
        while (node!=NULL)
        {
            if ((node->indv)!=last)
            {
                last=indv;
                DFS(node->indv,last);
            }
            node=node->next;
        }
    }
    for(i=0; i<graph->numv;++i)
    {
        if (Mark[i])
        {
            continue;
        }
        ++numc;
        DFS(i,last);
    }
    if (NUMC!=NULL)
    {
        (*NUMC)=numc;
    }
    return cycle;
}*/

int graph_dfs(t_graph *graph, size_t *NUMC)
{

    char Mark[graph->numv];//Массив маркеров
    size_t cycle=0;//Есть ли циклы или нет
    size_t numc=0;
    int i ,last;
    for (i=0;i<(graph->numv);++i)
    {
        Mark[i]=0;
    }
    //Поиск в глубину
    void DFS (size_t indv,int last)
    {
        if (Mark[indv])
        {
            cycle=1;
            return cycle;
        }
        t_graph_node *node = graph ->vertex[indv];
        Mark[indv]=1;
        last=indv;
        while (node!=NULL)
        {
            if (node->indv!=last)
            {
                DFS(node->indv,node->indv);
            }
            node=node->next;
        }
    }
    for(i=0; i<graph->numv;++i)
    {
        if (Mark[i])
        {
            continue;
        }
        ++numc;
        DFS(i,-1);
    }
    if (NUMC!=NULL)
    {
        (*NUMC)=numc;
    }
    return cycle;
}

//Поиск в ширину
int BFS (t_graph *graph, size_t i1, size_t i2)
{
    char mark[graph->numv];
    if (i1==i2)
    {
        return 0;
    }
    memset(mark,0,graph->numv);
    mark[i1]=1;
    mark[i2]=2;
    t_list *list1=list_new();
    t_list *list2=list_new();
    list_add_elem(list1,i1);
    list_add_elem(list2,i2);
    int len=1;
    while (1)
    {
        t_list *buff=list_new();

        //Код для list1
        t_list_node *iter=list_get_head(list1);//Возвращет указатель на голову списка
        while (iter!=NULL)
        {
            size_t ind=(iter->val);//Значение элемента на который ссылается итератор
            t_graph_node *node=graph->vertex[ind];
            while(node!=NULL)
            {
               size_t v=node->indv;
               size_t m=mark[v];
               if (m==2)
               {
                    list_del(list1);
                    list_del(list2);
                    list_del(buff);
                    return len;
               }
               if (m!=1)
               {
                   mark[v]=1;
                   list_add_elem(buff,v);
               }
               node=node->next;
            }
            iter=iter->next;
        }
        (++len);
        list_del(list1);
        list1=buff;
        buff=list_new();

        //Дублируем код только для list2
        iter=list_get_head(list2);//Возвращет указатель на голову списка
        while (iter!=NULL)
        {
            size_t ind=iter->val;//Значение элемента на который ссылается итератор
            t_graph_node *node=graph->vertex[ind];
            while(node!=NULL)
            {
               size_t v=node->indv;
               size_t m=mark[v];
               if (m==1)
               {
                    list_del(list1);
                    list_del(list2);
                    list_del(buff);
                    return len;
               }
               if (m!=2)
               {
                   mark[v]=2;
                   list_add_elem(buff,v);
               }
               node=node->next;
            }
            iter=iter->next;
        }
        (++len);
        list_del(list2);
        list2=buff;
        buff=list_new();
        if ((list_get_size(list1)==0) || (list_get_size(list2)==0))
        {
            return -1;
        }
    }
}



void main()
{
    size_t NUMC;
    t_graph *graph;
    t_list *list;
    graph=graph_new(10);
    graph_add_edge(graph,1,2);
    graph_add_edge(graph,2,3);
    graph_add_edge(graph,3,4);
    graph_add_edge(graph,4,5);
    graph_add_edge(graph,5,6);
    graph_add_edge(graph,6,7);
    if (graph_dfs(graph,&NUMC)==1)
    {
        printf("There are cycles\n");
    }
    else
    {
        printf("Nope cycles\n");
    }
    printf("Connected Components |%i|\n",NUMC);
    printf("The length between the vertices |%i|\n",BFS(graph,1,7));
    graph_del(graph);

/*
Задачи
1) Провери граф на связность.
2) Проверить граф на ацекличность (является ли деревом или нет).
3) Подсчитать компоненты связности в графе.
(обход в глубину)
4) Найти кротчайший маршрут между двумя заданными вершинами (обход в ширину).
5) Реализовать алгоритм Дейкстры.
*/
}
