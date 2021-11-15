# Example of Kubernetes Operator Using Quarkus

This repo is an example of a Kubernetes Operator built using [Quarkus](https://quarkus.io/).

[Link to the article]()

## Prerequisites

You need first to create a service principal as well as a Kubernetes secret:

```bash
az ad sp create-for-rbac --name MyFirstServicePrincipal
kubectl create secret generic azure-resource-operator-secret \
    --from-literal=tenant-id="${tenant_id}" \
    --from-literal=client-id="${client_id}" \
    --from-literal=client-secret="${client_secret}" \
    --from-literal=subscription-id="${subscription_id}" 
```

## Build the project

To build the project run:

```bash
./lifecycle.sh build
# or
./mvn clean install -DskipTests
```

## Deploy on a k3d cluster

To deploy on a k3d cluster run:

```bash
./lifecycle.sh deploy
```

## Run on a remote kubernetes cluster

To deploy on a remote Kubernetes Cluster you need to:

- Push the image on your registry
- Update the registry in the `helm/values`
- Run: 

```bash
helm install <release-name> helm
```

## Test sample resources:

```
kubectl apply -f examples/resource-group.yml
```

Wait until it is deployed on Azure.

```
kubectl apply -f examples/namespace.yml
```

Wait until it is deployed on Azure.

```
kubectl apply -f examples/queue.yml
```

